package cn.tycoding.langchat.server.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.server.utils.MybatisUtil;
import cn.tycoding.langchat.server.utils.QueryPage;
import cn.tycoding.langchat.server.utils.R;
import cn.tycoding.langchat.server.entity.LcLog;
import cn.tycoding.langchat.server.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2023/11/22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/langchat/log")
public class LogController {

    private final LogService logService;

    @GetMapping("/page")
    public R<Dict> list(LcLog sysLcLog, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(logService.list(sysLcLog, queryPage)));
    }

    @GetMapping("/{id}")
    public R<LcLog> findById(@PathVariable Long id) {
        return R.ok(logService.getById(id));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@auth.hasAuth('system:log:delete')")
    public R delete(@PathVariable Long id) {
        logService.delete(id);
        return R.ok();
    }
}
