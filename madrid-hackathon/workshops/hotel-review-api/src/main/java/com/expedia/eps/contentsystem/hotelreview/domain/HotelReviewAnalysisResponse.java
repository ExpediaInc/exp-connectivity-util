package com.expedia.eps.contentsystem.hotelreview.domain;

public class HotelReviewAnalysisResponse {

    private boolean success;
    private Result result;

    public HotelReviewAnalysisResponse(boolean success, Result result) {
        this.success = success;
        this.result = result;
    }

    public HotelReviewAnalysisResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
