/*
 * Copyright (c) 2016 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 * 
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
package cn.sixlab.web.mvc.tool;

import cn.sixlab.web.bean.ToolLinkGroup;
import cn.sixlab.web.bean.ToolLinkItem;
import cn.sixlab.web.mapper.ToolLinkGroupMapper;
import cn.sixlab.web.mapper.ToolLinkItemMapper;
import cn.sixlab.web.util.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
@Service
public class LinkService {
    private static Logger logger = LoggerFactory.getLogger(LinkService.class);

    @Autowired
    private ToolLinkItemMapper itemMapper;
    @Autowired
    private ToolLinkGroupMapper groupMapper;

    public void addItem(ToolLinkItem toolLinkItem) {
        toolLinkItem.setIsDeleted("0");
        itemMapper.insert(toolLinkItem);
    }

    public void delItem(int id) {
        ToolLinkItem item = new ToolLinkItem();
        item.setId(id);
        item.setIsDeleted("1");
        itemMapper.updateByPrimaryKeySelective(item);
    }

    public void updateItem(ToolLinkItem toolLinkItem) {
        toolLinkItem.setIsDeleted("0");
        itemMapper.updateByPrimaryKey(toolLinkItem);
    }

    public void searchItem(JsonMap json, String keyword, int id) {
        List<ToolLinkItem> itemList = itemMapper.queryKeyword(keyword, id);
        int num = 0;
        if (!CollectionUtils.isEmpty(itemList)) {
            num = itemList.size();
            json.put("items", itemList);
        }
        json.put("num", num);
    }

    public void addGroup(ToolLinkGroup toolLinkGroup) {
        toolLinkGroup.setIsDeleted("0");
        groupMapper.insert(toolLinkGroup);
    }

    public void delGroup(int id) {
        ToolLinkGroup group = new ToolLinkGroup();
        group.setId(id);
        group.setIsDeleted("1");
        groupMapper.updateByPrimaryKeySelective(group);
    }

    public void updateGroup(ToolLinkGroup toolLinkGroup) {
        toolLinkGroup.setIsDeleted("0");
        groupMapper.updateByPrimaryKey(toolLinkGroup);
    }

    public void searchGroup(JsonMap json, String keyword) {
        List<ToolLinkGroup> groupList = groupMapper.queryKeyword(keyword);
        int num = 0;
        if (!CollectionUtils.isEmpty(groupList)) {
            num = groupList.size();
            json.put("groups", groupList);
        }
        json.put("num", num);
    }

    public void group(JsonMap json, int id) {
        List<ToolLinkItem> itemList = itemMapper.queryByGroup(id);
        int num = 0;
        if (!CollectionUtils.isEmpty(itemList)) {
            num = itemList.size();
            json.put("items", itemList);
        }
        json.put("num", num);
    }
}
