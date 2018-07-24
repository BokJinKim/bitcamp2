// 서비스 컴포넌트 - 게시물 관련 업무를 처리할 객체
package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.Planner;
import bitcamp.java106.pms.domain.TravelPlan;

public interface PlannerService {
    // 서비스 컴포넌트에서 메서드명을 지을 때는 
    // 업무 용어를 사용하라!
    List<Planner> list(int pageNo, int pageSize);
    Planner get(int no);
    int add(Planner planner);
    int update(Planner planner);
    int delete(int no);
}

//ver 53 - 인터페이스 추가







