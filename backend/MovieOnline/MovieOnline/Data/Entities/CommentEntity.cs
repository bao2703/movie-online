using System;

namespace MovieOnline.Data.Entities
{
    public class CommentEntity
    {
        public int Id { get; set; }

        public string Content { get; set; }

        public DateTime DateCreated { get; set; }

        public string UserId { get; set; }

        public int? MovieId { get; set; }

        public int? EpisodeId { get; set; }
    }
}