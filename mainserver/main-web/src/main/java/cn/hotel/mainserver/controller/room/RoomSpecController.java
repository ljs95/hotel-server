package cn.hotel.mainserver.controller.room;

import cn.hotel.mainserver.model.room.RoomSpec;
import cn.hotel.mainserver.model.room.RoomType;
import cn.hotel.mainserver.pageination.PaginationResult;
import cn.hotel.mainserver.pageination.room.RoomSpecPage;
import cn.hotel.mainserver.service.room.RoomSpecService;
import cn.hotel.mainserver.service.room.RoomTypeService;
import cn.hotel.mainserver.util.response.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room/spec")
public class RoomSpecController {

    @Autowired
    RoomSpecService roomSpecService;

    @Autowired
    RoomTypeService roomTypeService;

    @GetMapping("/index")
    public ResponseVo index() {
        List<RoomType> roomTypeList = roomTypeService.select();
        Map<String, Object> map = new HashMap<>();
        map.put("roomTypeList", roomTypeList);
        return ResponseVo.successData(map);
    }

    @PostMapping("/table")
    public ResponseVo table(@RequestBody RoomSpecPage roomSpecPage) {
        PaginationResult result = roomSpecService.table(roomSpecPage);
        return ResponseVo.successData(result);
    }

    @GetMapping("/find/{id}")
    public ResponseVo find(@PathVariable Integer id) {
        RoomSpec roomSpec = roomSpecService.selectByPrimaryKey(id);
        return ResponseVo.successData(roomSpec);
    }

    @PutMapping("/create")
    public ResponseVo create(@RequestBody @Valid RoomSpec roomSpec) {
        roomSpec.priceYuanToFen();
        roomSpecService.insert(roomSpec);
        return ResponseVo.success("创建成功");
    }

    @PostMapping("/update/{id}")
    public ResponseVo update(@PathVariable @NotNull(message = "id不能为空") Integer id, @RequestBody @Valid RoomSpec roomSpec) {
        roomSpec.setId(id);
        roomSpec.priceYuanToFen();
        roomSpecService.updateByPrimaryKey(roomSpec);
        return ResponseVo.success("更新成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        roomSpecService.deleteByPrimaryKey(id);
        return ResponseVo.success("删除成功");
    }
}
