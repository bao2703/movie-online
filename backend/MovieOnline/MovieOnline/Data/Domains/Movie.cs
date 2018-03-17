using System;
using System.Collections.Generic;

namespace MovieOnline.Data.Domains
{
    public class Movie
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public DateTime Release { get; set; }

        public string Description { get; set; }

        public long Views { get; set; }

        public float Rating { get; set; }

        public string Poster { get; set; }

        public ICollection<GenreMovie> GenreMovies { get; set; }

        public ICollection<Episode> Episodes { get; set; }

        public ICollection<Like> Likes { get; set; }

        public ICollection<Comment> Comments { get; set; }
    }
}