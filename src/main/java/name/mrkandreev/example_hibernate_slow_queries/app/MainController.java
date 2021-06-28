package name.mrkandreev.example_hibernate_slow_queries.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
  private final MainService mainService;

  @PostMapping
  public String handler(@RequestParam Double sleepTimeout) {
    return mainService.handle(sleepTimeout);
  }
}
