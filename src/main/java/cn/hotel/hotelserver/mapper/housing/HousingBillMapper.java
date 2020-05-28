package cn.hotel.hotelserver.mapper.housing;


import cn.hotel.hotelserver.model.housing.HousingBill;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingBillMapper {
    int deleteByPrimaryKey(String serial);

    int insert(HousingBill record);

    int insertSelective(HousingBill record);

    HousingBill selectByPrimaryKey(String serial);

    int updateByPrimaryKeySelective(HousingBill record);

    int updateByPrimaryKeyWithBLOBs(HousingBill record);

    int updateByPrimaryKey(HousingBill record);
}