using System;
using System.ComponentModel.DataAnnotations;

namespace MovieOnline.Data.Bases
{
    public class Movie
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Description { get; set; }

        public long Views { get; set; }

        public string PosterUrl { get; set; }

        public DateTime DateCreated { get; set; }
    }
}