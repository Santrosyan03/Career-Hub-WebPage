package account.database.management.system.controller.get;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class GetControllerBase {

    @GetMapping("")
    public ResponseEntity<String> getBase() {
        return new ResponseEntity<>("Welcome to website!", HttpStatusCode.valueOf(200));
    }
}
