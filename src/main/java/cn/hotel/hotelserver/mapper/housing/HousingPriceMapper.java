package cn.hotel.hotelserver.mapper.housing;

import cn.hotel.hotelserver.model.housing.HousingPrice;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingPrice record);

    HousingPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingPrice record);

    int updateByPrimaryKeyWithBLOBs(HousingPrice record);

    int updateByPrimaryKey(HousingPrice record);
}