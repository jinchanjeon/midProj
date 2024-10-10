package kr.or.ddit.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.or.ddit.service.MovieServiceImpl;
import kr.or.ddit.service.iMovieService;
import kr.or.ddit.util.ScanUtil;
import kr.or.ddit.vo.MovieVo;


public class ApiExplorer2 {
	public static void main(String[] args) throws Exception {
		ApiExplorer2 ae2 = new ApiExplorer2();
		//ae2.process();
	}
	

	// 영화 검색했을 때 디비에서 찾아보고 없으면 api에 찾아서 db에 insert하는 클래스

	iMovieService movieService = MovieServiceImpl.getInstance();


//	public void process() {
//		List<MovieVo> movieList = movieService.movieList();
//		for(MovieVo movie: movieList) {
//			System.out.println(movie);
//		}
//		String name = ScanUtil.nextLine("조회할 영화 입력: ");
//		MovieVo selectMovie = new MovieVo();
//		for(MovieVo movie:movieList) {
//			String movieName =movie.getMovieName();
//			if(movieName.equals(name)) {
//				selectMovie=movie;
//			}
//		}
//		
//		MovieVo MovieName= new MovieVo();
//		MovieName.setMovieName(name);
//		//무비 디비에 있는지 조회
//		List<MovieVo> movie = movieService.getMovieName(MovieName);
//		
//		//영화가 디비에 없다면 api에서 가져옴
//		if(movie == null) {
//			MovieVo api;
//			try {
//				api = getAPI(name);
//				System.out.println(api);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}


	public MovieVo getAPI(String movieName) throws Exception {
		StringBuilder urlBuilder = new StringBuilder("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2"); 
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("FFL9003M35F6J0562575", "utf-8")); /* Service Key */
        urlBuilder.append("&" + URLEncoder.encode("detail", "UTF-8") + "=" + URLEncoder.encode("Y", "utf-8"));
        urlBuilder.append("&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(movieName, "utf-8"));

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
        
        ApiExplorer ae = new ApiExplorer();
        ae.parseMovies(sb.toString());
        
		return null;
	}


	

}