// 업무로직 구현체 - 고객사 마다 다른 구현을 할 수 있다.
package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.TravelPlanDao;
import bitcamp.java106.pms.domain.TravelPlan;
import bitcamp.java106.pms.service.TravelPlanService;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {
    
    TravelPlanDao travelPlanDao;
    
    public TravelPlanServiceImpl(TravelPlanDao travelPlanDao) {
        this.travelPlanDao = travelPlanDao;
    }
    
    @Override
    public List<TravelPlan> list(int plno, int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("plno", plno);
        
        return travelPlanDao.selectList(params);
    }
    
    @Override
    public TravelPlan get(int no) {
        return travelPlanDao.selectOne(no);
    }
    
    @Override
    public int add(TravelPlan travelPlan) {
        return travelPlanDao.insert(travelPlan);
    }
    
    @Override
    public int update(TravelPlan travelPlan) {
        return travelPlanDao.update(travelPlan);
    }
    
    @Override
    public int delete(int no) {
        return travelPlanDao.delete(no);
    }
}

//ver 53 - 클래스 추가






