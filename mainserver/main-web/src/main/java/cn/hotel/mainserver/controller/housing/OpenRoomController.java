package cn.hotel.mainserver.controller.housing;

import cn.hotel.mainserver.model.housing.HousingBill;
import cn.hotel.mainserver.model.room.Room;
import cn.hotel.mainserver.model.room.RoomSpec;
import cn.hotel.mainserver.service.housing.OpenRoomService;
import cn.hotel.mainserver.service.housing.bo.OpenRoomBo;
import cn.hotel.mainserver.service.room.RoomService;
import cn.hotel.mainserver.service.room.RoomTypeService;
import cn.hotel.mainserver.util.response.ResponseVo;
import cn.hotel.mainserver.vo.housing.OpenRoomVo;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    OpenRoomBo.Builder openBuilder;

    /**
     * 开全日房
     * @param openRoomVo
     * @return
     */
    @PostMapping("/openDay")
    public ResponseVo openDayRoom(@RequestBody OpenRoomVo openRoomVo) throws InterruptedException {
        BeanUtils.copyProperties(openRoomVo, openBuilder);
        OpenRoomBo.Builder builder = openBuilder.setMode(RoomSpec.modeEnum.MODE_DAY);

        HousingBill bill = openRoomService.openRoom(builder);
        return ResponseVo.success("开房成功，账单号：" + bill.getSerial());
    }

    /**
     * 开钟点房
     * @param openRoomVo
     * @return
     */
    @PostMapping("/openHour")
    public ResponseVo openHourRoom(@RequestBody OpenRoomVo openRoomVo) throws InterruptedException {
        BeanUtils.copyProperties(openRoomVo, openBuilder);
        OpenRoomBo.Builder builder = openBuilder.setMode(RoomSpec.modeEnum.MODE_HOUR);

        HousingBill bill = openRoomService.openRoom(builder);
        return ResponseVo.success("开房成功，账单号：" + bill.getSerial());
    }
}
