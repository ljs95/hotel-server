package cn.hotel.hotelserver.controller.room;

import cn.hotel.hotelserver.model.room.RoomSpec;
import cn.hotel.hotelserver.model.room.RoomType;
import cn.hotel.hotelserver.service.room.RoomSpecService;
import cn.hotel.hotelserver.service.room.RoomTypeService;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.util.bean.ColaBeanUtils;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.room.RoomTypeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room/type")
public class RoomTypeController {
    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    RoomSpecService roomSpecService;

    @GetMapping("/index")
    public ResponseVo index() {
        List<RoomSpec> roomSpecList = roomSpecService.select();
        Map<String, Object> map = new HashMap<>();
        map.put("specList", roomSpecList);
        return ResponseVo.successData(map);
    }

    @GetMapping("/table")
    public ResponseVo table(Integer current, Integer size) {
        Page<RoomType> roomTypePage = new Page<>(current, size);
        PaginationResult table = roomTypeService.table(roomTypePage);
        return ResponseVo.successData(table);
    }

    @GetMapping("/find/{id}")
    public ResponseVo find(@PathVariable Integer id) {
        RoomType roomType = roomTypeService.find(id);
        return ResponseVo.successData(roomType);
    }

    @PutMapping("/create")
    public ResponseVo create(@RequestBody RoomTypeVo roomTypeVo) {
        RoomType roomType = new RoomType();
        ColaBeanUtils.copyProperties(roomTypeVo, roomType);
        roomTypeService.create(roomType);
        return ResponseVo.success("创建成功");
    }

    @PostMapping("/update")
    public ResponseVo update(@RequestBody RoomTypeVo roomTypeVo) {
        RoomType roomType = new RoomType();
        ColaBeanUtils.copyProperties(roomTypeVo, roomType);
        roomTypeService.update(roomType);
        return ResponseVo.success("创建成功");
    }

    @PostMapping("/applySpec/{id}/{specId}")
    public ResponseVo applySpec(@PathVariable Integer id, @PathVariable Integer specId) {
        roomTypeService.applySpec(id, specId);
        return ResponseVo.success("应用成功");
    }

    @DeleteMapping("/applySpec/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        roomTypeService.delete(id);
        return ResponseVo.success("删除成功");
    }
}
