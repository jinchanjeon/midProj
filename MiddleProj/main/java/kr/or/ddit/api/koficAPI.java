package kr.or.ddit.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import java.time.format.DateTimeFormatter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.vo.ActorVo;
import kr.or.ddit.vo.BoxofficeVo;
import kr.or.ddit.vo.DirectorVo;
import kr.or.ddit.vo.GenreVo;
import kr.or.ddit.vo.MovGenVo;
import kr.or.ddit.vo.MovieActorVo;
import kr.or.ddit.vo.MovieDirectorVo;
import kr.or.ddit.vo.MovieVo;

public class koficAPI {
	public static void main(String[] args) throws Exception {
		koficAPI ka = new koficAPI();
		ka.getAPI();

	}

	iMovieService movieService = MovieServiceImpl.getInstance();

	public void getAPI() throws Exception {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		String yestStr = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		StringBuilder urlBuilder = new StringBuilder(
				"http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json");
		urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "="
				+ URLEncoder.encode("de8022431ff65b017292da8f4ccb5ef6", "utf-8")); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "=" + URLEncoder.encode(yestStr, "utf-8"));

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		// API 요청 후 응답 출력
		System.out.println("API URL: " + urlBuilder.toString());
		System.out.println("Response Code: " + conn.getResponseCode());

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		parseBoxOffice(sb.toString());

		System.out.println("Response: " + sb.toString());
	}

	public void parseBoxOffice(String jsonString) throws JsonSyntaxException {
		System.out.println("Received JSON: " + jsonString);

	    Gson gson = new Gson();
		JsonObject jsonObject;
		    
		try {
		    jsonObject = gson.fromJson(jsonString, JsonObject.class);
		// JSON 구조 검증
		if (!jsonObject.has("boxOfficeResult")) {
			System.out.println("Unexpected response structure: " + jsonString);
			return;
		}
		
			JsonObject boxOfficeResult = gson.fromJson(jsonString, JsonObject.class);
//			System.out.println("a"+boxOfficeResult);
			JsonObject result = boxOfficeResult.getAsJsonObject("boxOfficeResult");
//			System.out.println("dd"+result);
			JsonArray resultArray = result.getAsJsonArray("dailyBoxOfficeList");
			System.out.println(resultArray);
			for (int j = 0; j < resultArray.size(); j++) {
				JsonObject movie = resultArray.get(j).getAsJsonObject();

				// 영화 정보 추출
				int rank = movie.get("rank").getAsInt();
				String movieCode = movie.get("movieCd").getAsString();
				String movieName = movie.get("movieNm").getAsString().trim().replaceAll(" ", "").toUpperCase();
				
				String releaseDate = movie.get("openDt").getAsString();
				int audi = movie.get("audiCnt").getAsInt();
				int people = movie.get("audiAcc").getAsInt();

				// string -> localdate형식으로 바꾼 뒤에 format 형식 변형
				int releaseDateInt = Integer
						.parseInt(LocalDate.parse(releaseDate).format(DateTimeFormatter.ofPattern("yyyyMMdd")));

				BoxofficeVo boxoffice = new BoxofficeVo();
				LocalDate yesterday = LocalDate.now().minusDays(1);
				String yestStr = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				int yest = Integer.parseInt(yestStr);

				// 박스오피스 정보 설정
				boxoffice.setRank(rank);
				boxoffice.setMovieName(movieName);
				boxoffice.setBoxofficeDate(yest);
				boxoffice.setReleaseDate(releaseDateInt);
				boxoffice.setTotalPeople(people);
				boxoffice.setTodayPeople(audi);

//				System.out.println("boxoffice:"+boxoffice);

				BoxofficeVo existingBoxOffice = movieService.boxOfficeExists(boxoffice.getMovieName());
				System.out.println("box table에 있는지 확인:"+existingBoxOffice);
				if (existingBoxOffice != null) {
				    //System.out.println("데이터 이미 존재: " + yest + ", 영화 제목: " + movieName);
					searchMovie(movieName, releaseDateInt);
				    continue;
				}
				
				movieService.insertBox(boxoffice);
				
				try {
					System.out.println("searchmovie실행하고있니?");
					searchMovie(movieName, releaseDateInt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 } catch (JsonSyntaxException e) {
		        System.out.println("JSON 파싱 오류: " + e.getMessage());
		        System.out.println("받은 JSON: " + jsonString);
		    } catch (Exception e) {
		        System.out.println("예외 발생: " + e.getMessage());
		        e.printStackTrace();
		    }
	}

	public void searchMovie(String movieName, int releaseDateInt) throws Exception {
		// db에 찾아봐(movie table에 10개의 영화가 있는지)

		MovieVo movie = new MovieVo();
		movie.setMovieName(movieName);
		movie.setMovieRelease(releaseDateInt);

		MovieVo movieVO = movieService.getMovieNameRelease(movie);
		System.out.println("moie에 있니?"+movieVO);
		System.out.println("---------------");

		// db에 없으면 kmdb api에 요청
		if (movieVO == null) {
			StringBuilder urlBuilder = new StringBuilder(
					"http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2");
			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
					+ URLEncoder.encode("FFL9003M35F6J0562575", "utf-8")); /* Service Key */
			urlBuilder.append("&" + URLEncoder.encode("detail", "UTF-8") + "=" + URLEncoder.encode("Y", "utf-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(movie.getMovieName(), "utf-8"));
			urlBuilder.append("&" + URLEncoder.encode("releaseDts", "UTF-8") + "="
					+ URLEncoder.encode(movie.getMovieRelease() + "", "utf-8"));

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			ApiExplorer ae= new ApiExplorer();
			ae.parseMovies(sb.toString());
//			parseMovies(sb.toString());
		}

	}
	
	


}