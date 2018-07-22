// 업무로직 구현체 - 고객사 마다 다른 구현을 할 수 있다.
package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.TravelLogDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.domain.TravelLog;
import bitcamp.java106.pms.service.TravelLogService;

@Service
public class TravelLogServiceImpl implements TravelLogService {
    
    TravelLogDao travelLogDao;
    
    public TravelLogServiceImpl(TravelLogDao travelLogDao) {
        this.travelLogDao = travelLogDao;
    }
    
    @Override
    public List<TravelLog> list(int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        
        return travelLogDao.selectList(params);
    }
    
//    @Override
//    public Team get(String name) {
//        return teamDao.selectOne(name);
//    }
//    
//    @Override
//    public Team getWithMembers(String name) {
//        return teamDao.selectOneWithMembers(name);
//    }
//    
//    @Override
//    public int add(Team member) {
//        return teamDao.insert(member);
//    }
//    
//    @Override
//    public int update(Team member) {
//        return teamDao.update(member);
//    }
//    
//    @Override
//    public int delete(String name) {
//        HashMap<String,Object> params = new HashMap<>();
//        params.put("teamName", name);
//        
//        teamMemberDao.delete(params);
//        taskDao.deleteByTeam(name);
//        return teamDao.delete(name);
//    }
//    
//    @Override
//    public boolean isMember(String teamName, String memberId) {
//        HashMap<String,Object> params = new HashMap<>();
//        params.put("teamName", teamName);
//        params.put("memberId", memberId);
//        
//        return teamMemberDao.isExist(params);
//    }
//    
//    @Override
//    public int addMember(String teamName, String memberId) {
//        HashMap<String,Object> params = new HashMap<>();
//        params.put("teamName", teamName);
//        params.put("memberId", memberId);
//        
//        return teamMemberDao.insert(params);
//    }
//    
//    @Override
//    public int deleteMember(String teamName, String memberId) {
//        HashMap<String,Object> params = new HashMap<>();
//        params.put("teamName", teamName);
//        params.put("memberId", memberId);
//        
//        return teamMemberDao.delete(params);
//    }
//    
//    @Override
//    public List<Member> getMembersWithEmail(String teamName) {
//        return teamMemberDao.selectListWithEmail(teamName);
//    }
}

//ver 53 - 클래스 추가






