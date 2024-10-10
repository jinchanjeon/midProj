package kr.or.ddit.movie.service;

import java.util.List;

import kr.or.ddit.movie.Member; // Member 클래스 import
import kr.or.ddit.movie.dao.MemberDao; // MemberDao 클래스 import

public class MemberService {
    private MemberDao memberDao = new MemberDao(); // MemberDao 인스턴스 생성

    public void addMember(Member member) {
        memberDao.addMember(member);
    }

    public void updateMember(int id, Member member) {
        memberDao.updateMember(id, member);
    }

    public void deleteMember(int id) {
        memberDao.deleteMember(id);
    }

    public List<Member> getAllMembers() {
        return memberDao.getAllMembers();
    }
}
