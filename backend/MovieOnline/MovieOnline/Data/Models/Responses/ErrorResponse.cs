namespace MovieOnline.Data.Models.Responses
{
    public class ErrorResponse
    {
        public static ErrorResponse InvalidPayload = new ErrorResponse(0, "InvalidPayload");
        public static ErrorResponse EmailConflict = new ErrorResponse(100, "EmailConflict");
        public static ErrorResponse EmailNotFound = new ErrorResponse(101, "EmailNotFound");
        public static ErrorResponse InvalidLogin = new ErrorResponse(102, "InvalidLogin");

        public ErrorResponse(int code, object message)
        {
            Code = code;
            Message = message;
        }

        public int Code { get; set; }

        public object Message { get; set; }
    }
}