package bitcamp.java106.pms.web.json;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.TravelPlan;
import bitcamp.java106.pms.service.TravelPlanService;

@RestController
@RequestMapping("/travelPlan")
public class TravelPlanController {
    
    TravelPlanService travelPlanService;
    
    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    @RequestMapping("form")
    public void form() {
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(TravelPlan travelPlan) throws Exception {
        travelPlanService.add(travelPlan);
    }
    
    @RequestMapping("add2")
    @ResponseStatus(HttpStatus.CREATED)
    public void add2(TravelPlan travelPlan) throws Exception {
        travelPlanService.add2(travelPlan);
    }
    
    @RequestMapping("delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("no") int no) throws Exception {
        travelPlanService.delete(no);
    }
    
    @RequestMapping("list{page}")
    public Object list(
            @MatrixVariable(defaultValue="1") int pageNo,
            @MatrixVariable(defaultValue="3") int pageSize) {
        
        return travelPlanService.list(pageNo, pageSize);
    }
    
    @RequestMapping("update")
    @ResponseStatus(HttpStatus.OK) // 기본 값이 OK 이다.
    public void update(@RequestParam(value="plday", defaultValue="0") int plday, 
    				   @RequestParam(value="tno", defaultValue="0")int tno, 
    				   String descr) throws Exception {
        System.out.println("asd");
        travelPlanService.update(plday, tno, descr);
    }
     
    @RequestMapping("{planno}/{no}")
    public List<TravelPlan> view(@PathVariable int planno, @PathVariable int no) throws Exception {
       System.out.printf("plno:%d, dayno:%d\n", planno, no);
        return travelPlanService.get(planno, no);
        
    }
    @RequestMapping("day/{planno}")
    public int titleView(@PathVariable int planno) throws Exception {
       System.out.printf("plno:%d\n", planno);
        return travelPlanService.get2(planno);
        
    }

    @RequestMapping("{no}")
    public List<TravelPlan> selectOne(@PathVariable int no) throws Exception {
        return travelPlanService.selectOne(no);
        
    }
    
}

//ver 53 - DAO 대신 Service 객체 사용
//ver 52 - InternalResourceViewResolver 적용
//         *.do 대신 /app/* 을 기준으로 URL 변경
//         페이지 관련 파라미터에 matrix variable 적용
//ver 51 - Spring WebMVC 적용
//ver 49 - 요청 핸들러의 파라미터 값 자동으로 주입받기
//ver 48 - CRUD 기능을 한 클래스에 합치기
//ver 47 - 애노테이션을 적용하여 요청 핸들러 다루기
//ver 46 - 페이지 컨트롤러를 POJO를 변경
//ver 45 - 프론트 컨트롤러 적용
//ver 42 - JSP 적용
//ver 40 - 필터 적용
//ver 39 - forward 적용
//ver 38 - redirect 적용
//ver 37 - TravelPlanAddController 클래스를 서블릿으로 변경
//         출력 결과를 HTML로 변경
//         자동 Refresh 태그 추가
//ver 31 - JDBC API가 적용된 DAO 사용
//ver 28 - 네트워크 버전으로 변경
//ver 26 - TravelPlanController에서 add() 메서드를 추출하여 클래스로 정의. 














