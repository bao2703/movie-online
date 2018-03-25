namespace MovieOnline.Data.Models.Reponses
{
    public class ErrorReponse
    {
        public static ErrorReponse InvalidPayload = new ErrorReponse(0, "InvalidPayload");
        public static ErrorReponse EmailConflict = new ErrorReponse(100, "EmailConflict");
        public static ErrorReponse EmailNotFound = new ErrorReponse(101, "EmailNotFound");
        public static ErrorReponse InvalidLogin = new ErrorReponse(102, "InvalidLogin");

        public ErrorReponse(int code, object message)
        {
            Code = code;
            Message = message;
        }

        public int Code { get; set; }

        public object Message { get; set; }
    }
}