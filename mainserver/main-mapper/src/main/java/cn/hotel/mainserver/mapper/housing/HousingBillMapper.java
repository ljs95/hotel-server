package cn.hotel.mainserver.mapper.housing;


import cn.hotel.mainserver.model.housing.HousingBill;
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