package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpUsersBinfoKey
 * Description:用户基本信息主键
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午4:47:36
 *
 */
public class SpUsersBinfoKey  implements Serializable {
	
	private Integer spId;

	private Integer spAtuid;

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpAtuid() {
		return spAtuid;
	}

	public void setSpAtuid(Integer spAtuid) {
		this.spAtuid = spAtuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((spId == null) ? 0 : spId.hashCode());
		result = prime * result + ((spAtuid == null) ? 0 : spAtuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpUsersBinfoKey other = (SpUsersBinfoKey) obj;
		if (spId == null) {
			if (other.spId != null)
				return false;
		} else if (!spId.equals(other.spId))
			return false;
		if (spAtuid == null) {
			if (other.spAtuid != null)
				return false;
		} else if (!spAtuid.equals(other.spAtuid))
			return false;
		return true;
	}
}
