package com.lilac.pojo.VO;

import com.lilac.pojo.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouterVO {

    private List<Menu> menus;
}
