using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Domains
{
    public class Comment : CommentEntity
    {
        public User User { get; set; }

        public Movie Movie { get; set; }

        public Episode Episode { get; set; }
    }
}