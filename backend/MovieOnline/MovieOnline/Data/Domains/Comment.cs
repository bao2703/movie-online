using System;

namespace MovieOnline.Data.Domains
{
    public class Comment
    {
        public int Id { get; set; }

        public string Content { get; set; }

        public DateTime DateCreated { get; set; }

        public string UserId { get; set; }

        public User User { get; set; }

        public int? MovieId { get; set; }

        public Movie Movie { get; set; }

        public int? EpisodeId { get; set; }

        public Episode Episode { get; set; }
    }
}