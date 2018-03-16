namespace MovieOnline.Data.Errors
{
    public class Error
    {
        public static Error InvalidPayload = new Error(0, "InvalidPayload");
        public static Error EmailConflict = new Error(100, "EmailConflict");
        public static Error EmailNotFound = new Error(101, "EmailNotFound");
        public static Error InvalidLogin = new Error(102, "InvalidLogin");

        public Error(int code, object message)
        {
            Code = code;
            Message = message;
        }

        public int Code { get; set; }

        public object Message { get; set; }
    }
}