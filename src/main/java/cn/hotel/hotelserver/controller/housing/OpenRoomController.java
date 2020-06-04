package cn.hotel.hotelserver.controller.housing;

import cn.hotel.hotelserver.model.housing.HousingBill;
import cn.hotel.hotelserver.model.room.Room;
import cn.hotel.hotelserver.model.room.RoomSpec;
import cn.hotel.hotelserver.service.housing.OpenRoomService;
import cn.hotel.hotelserver.service.room.RoomService;
import cn.hotel.hotelserver.service.room.RoomTypeService;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.vo.housing.OpenRoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/housing/open_room")
public class OpenRoomController {

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    RoomService roomService;

    @Autowired
    OpenRoomService openRoomService;

    @GetMapping("/index")
    public ResponseVo index() {
        Map<String, Object> map = new HashMap<>();
        map.put("roomTypes", roomTypeService.select());

        return ResponseVo.successData(map);
    }

    @GetMapping("/rooms")
    public ResponseVo selectRoomsByTypeId(@RequestParam Integer typeId) {
        List<Room> roomList = roomService.selectRoomsByTypeId(typeId);
        return ResponseVo.successData(roomList);
    }

    /**
     * 开全日房
     * @param openRoomVo
     * @return
     */
    @PostMapping("/openDay")
    public ResponseVo openDayRoom(@RequestBody OpenRoomVo openRoomVo) throws InterruptedException {
        openRoomVo.setMode(RoomSpec.MODE_DAY);
        HousingBill bill = openRoomService.openRoom(openRoomVo);
        return ResponseVo.success("开房成功，账单号：" + bill.getSerial());
    }
}
