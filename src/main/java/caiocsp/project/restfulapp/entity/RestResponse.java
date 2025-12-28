package caiocsp.project.restfulapp.entity;

import java.util.HashMap;

public class RestResponse<T> {

	public static <T> Object response(T object) {
		return new HashMap<String, T>() {
			private static final long serialVersionUID = 1L;
			{
				put("result", (T) object);
			}
		};

	}

}
