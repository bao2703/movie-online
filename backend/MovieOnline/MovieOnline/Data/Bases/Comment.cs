using System;

namespace MovieOnline.Data.Bases
{
    public class Comment
    {
        public int Id { get; set; }

        public string Content { get; set; }

        public DateTime DateCreated { get; set; }

        public int UserId { get; set; }

        public int? MovieId { get; set; }

        public int? EpisodeId { get; set; }
    }
}