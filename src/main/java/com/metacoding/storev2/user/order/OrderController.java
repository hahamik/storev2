package com.metacoding.storev2.user.order;

import com.metacoding.storev2.user.User;
import com.metacoding.storev2.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/order/list")
    public String orderList(HttpServletRequest request) {
        List<OrderResponse.OrderDTO> orderList = orderService.구매목록();
        request.setAttribute("orderList", orderList);
        return "order/list";
    }


    @PostMapping("/order/save")
    public String order(@RequestParam(name = "storeId", required = true) int storeId,
                        @RequestParam(name = "qty", required = true) int qty,
                        HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        if (user == null) throw new RuntimeException("로그인 안됨");
        orderService.구매하기(storeId, user.getId(), qty);
        return "redirect:/order/list";
    }

}
