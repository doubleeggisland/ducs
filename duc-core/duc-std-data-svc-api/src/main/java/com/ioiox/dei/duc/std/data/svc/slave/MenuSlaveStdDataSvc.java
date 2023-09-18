package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;

import java.util.List;

public interface MenuSlaveStdDataSvc {
    int countByParam (final MenuQueryParam queryParam);
    List<MenuSlaveVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                       final MenuQueryCfg queryCfg);
    MenuSlaveVO getByPk(final Long menuId,
                        final MenuQueryCfg queryCfg);
    List<MenuSlaveVO> queryByPks(final List<Long> menuIds,
                                 final MenuQueryCfg queryCfg);
    List<MenuSlaveVO> queryByParam(final MenuQueryParam queryParam,
                                   final MenuQueryCfg queryCfg);
}
