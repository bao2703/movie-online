namespace MovieOnline.Data.Entities
{
    public class LikeEntity
    {
        public int MovieId { get; set; }

        public MovieEntity Movie { get; set; }

        public int UserId { get; set; }

        public UserEntity User { get; set; }
    }
}