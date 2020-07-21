package cn.hotel.mainserver.mapper.housing;

import cn.hotel.mainserver.model.housing.HousingPrice;
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