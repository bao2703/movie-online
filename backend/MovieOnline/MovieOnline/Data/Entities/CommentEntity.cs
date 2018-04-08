using MovieOnline.Data.Bases;

namespace MovieOnline.Data.Entities
{
    public class CommentEntity : Comment
    {
        public UserEntity User { get; set; }

        public MovieEntity Movie { get; set; }
    }
}