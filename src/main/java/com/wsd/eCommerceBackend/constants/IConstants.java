package com.wsd.eCommerceBackend.constants;

import com.wsd.eCommerceBackend.models.commons.ResponseModel;

public interface IConstants {
    /**
     * Converts an object dynamically to a response object for better API format with status message<p>
     *
     * @param data
     * @param <T>
     * @return ResponseModel
     */
    default <T> ResponseModel<T> convertToJSON(T data) {

        ResponseModel<T> result = new ResponseModel<T>();
        result.setData(data);

        return result;
    }
}
