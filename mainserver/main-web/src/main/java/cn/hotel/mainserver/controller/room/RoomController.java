package cn.hotel.mainserver.controller.room;

import cn.hotel.mainserver.model.room.Room;
import cn.hotel.mainserver.model.room.RoomType;
import cn.hotel.mainserver.pageination.PaginationResult;
import cn.hotel.mainserver.pageination.room.RoomPage;
import cn.hotel.mainserver.service.room.RoomService;
import cn.hotel.mainserver.service.room.RoomTypeService;
import cn.hotel.mainserver.util.response.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomTypeService roomTypeService;

    @GetMapping("/index")
    public ResponseVo index() {
        List<RoomType> roomTypeList = roomTypeService.select();
        Map<String, Object> map = new HashMap<>(2);
        map.put("roomTypeList", roomTypeList);
        map.put("statusList", Room.statusEnum.getStatusList());
        return ResponseVo.successData(map);
    }

    @PostMapping("/table")
    public ResponseVo table(@RequestBody RoomPage roomPage) {
        PaginationResult paginationResult = roomService.table(roomPage);
        return ResponseVo.successData(paginationResult);
    }

    @GetMapping("/find/{id}")
    public ResponseVo find(@PathVariable Integer id) {
        Room room = roomService.selectByPrimaryKey(id);
        return ResponseVo.successData(room);
    }

    @PutMapping("/create")
    public ResponseVo update(@RequestBody @Valid Room room) {
        roomService.insert(room);
        return ResponseVo.success("更新成功");
    }

    @PostMapping("/update/{id}")
    public ResponseVo update(@PathVariable Integer id, @RequestBody @Valid Room room) {
        room.setId(id);
        roomService.updateByPrimaryKey(room);
        return ResponseVo.success("更新成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        roomService.deleteByPrimaryKey(id);
        return ResponseVo.success("更新成功");
    }
}
