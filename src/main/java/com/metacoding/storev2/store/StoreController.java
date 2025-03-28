package com.metacoding.storev2.store;

import com.metacoding.storev2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StoreController {

    private final StoreService storeService;
    private final HttpSession session;


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/store/save-form")
    public String saveForm() {
        return "store/save-form";
    }

    @GetMapping("/store/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        request.setAttribute("model", storeService.상품상세(id));
        return "store/update-form";
    }


    @PostMapping("/store/save")
    public String save(StoreRequest.saveDTO saveDTO) {
        // 로그인 안되어 있으면 터뜨림
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");
        session.getAttribute("user");
        System.out.println(saveDTO.getName());
        System.out.println(saveDTO.getPrice());
        System.out.println(saveDTO.getStock());
        storeService.상품등록(saveDTO);
        return "redirect:/store/list";
    }

    @PostMapping("/store/{id}/update")
    public String update(@PathVariable("id") int id, StoreRequest.UpdateDTO updateDTO) {
        // TODO 등록한 사람이 수정
        storeService.상품수정(updateDTO, id);
        return "redirect:/store/{id}";
    }

    @PostMapping("/store/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        // TODO 등록한 사람이 삭제
        storeService.상품삭제(id);
        return "redirect:/store/list";
    }


    @GetMapping("/store/list")
    public String list(HttpServletRequest request) { // MVC
        List<StoreResponse.ListPageDTO> storeList = storeService.상품목록();
        request.setAttribute("models", storeList);
        System.out.println(storeList);
        return "store/list";
    }

    @GetMapping("/store/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {
        Store store = storeService.상품상세(id);

        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("model", store);
        session.setAttribute("sessionUser", sessionUser);
        return "store/detail";
    }

}
