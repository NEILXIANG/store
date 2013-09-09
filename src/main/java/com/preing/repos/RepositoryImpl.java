package com.preing.repos;

import java.util.ArrayList;
import java.util.List;

import com.preing.domain.product.Size;
import com.preing.domain.product.Size.Type;

public class RepositoryImpl implements Repository{

	private static List<Size> sizes=new ArrayList<Size>();
	static{
		sizes.add(new Size("01", "S",Type.CLOTHS));
		sizes.add(new Size("02", "M",Type.CLOTHS));
		sizes.add(new Size("03", "L",Type.CLOTHS));
		sizes.add(new Size("04", "XL",Type.CLOTHS));
		sizes.add(new Size("05", "XXL",Type.CLOTHS));
	}
	
	@Override
	public List<Size> getAllSize(){
		return sizes;
	}
}
