package cn.hotel.hotelserver.controller.room;

import cn.hotel.hotelserver.model.room.RoomType;
import cn.hotel.hotelserver.service.room.RoomTypeService;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.util.bean.ColaBeanUtils;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.room.RoomTypeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room/type")
public class RoomTypeController {
    @Autowired
    RoomTypeService roomTypeService;

    @GetMapping("/table")
    public ResponseVo table(Integer page, Integer size) {
        Page<RoomType> roomTypePage = new Page<>(page, size);
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
}
