package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.Partner;
import bitcamp.java106.pms.service.PartnerService;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    
    PartnerService partnerService;
    
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @RequestMapping("form")
    public void form() {
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public Object add(Partner partner) throws Exception {
        partnerService.add(partner);
        return partner.getPtno();
    }
    
    @RequestMapping("delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("no") int no) throws Exception {
        partnerService.delete(no);
    }
    
    @RequestMapping("list")
    public Object list(
            @MatrixVariable(defaultValue="1") int pageNo,
            @MatrixVariable(defaultValue="6") int pageSize) {
        
        return partnerService.list(pageNo, pageSize);
    }
    @RequestMapping("list{page}")
    public Object listwithPage(
            @MatrixVariable(defaultValue="1") int pageNo,
            @MatrixVariable(defaultValue="6") int pageSize) {
                
        return partnerService.listwithPage(pageNo, pageSize);
    }
    
    @RequestMapping("update")
    @ResponseStatus(HttpStatus.OK) // 기본 값이 OK 이다.
    public void update(Partner partner) throws Exception {
        partnerService.update(partner);
    }
    
    @RequestMapping("{no}")
    public Partner view(@PathVariable int no) throws Exception {
        return partnerService.get(no);
        
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
//ver 37 - PartnerAddController 클래스를 서블릿으로 변경
//         출력 결과를 HTML로 변경
//         자동 Refresh 태그 추가
//ver 31 - JDBC API가 적용된 DAO 사용
//ver 28 - 네트워크 버전으로 변경
//ver 26 - PartnerController에서 add() 메서드를 추출하여 클래스로 정의.