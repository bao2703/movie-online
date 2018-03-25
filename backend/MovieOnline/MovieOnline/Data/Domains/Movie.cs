using System.Collections.Generic;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Domains
{
    public class Movie : MovieEntity
    {
        public ICollection<GenreMovie> GenreMovies { get; set; }

        public ICollection<Episode> Episodes { get; set; }

        public ICollection<Like> Likes { get; set; }

        public ICollection<Comment> Comments { get; set; }
    }
}