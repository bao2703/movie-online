using System.Collections.Generic;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Domains
{
    public class Episode : EpisodeEntity
    {
        public Movie Movie { get; set; }

        public ICollection<Comment> Comments { get; set; }
    }
}