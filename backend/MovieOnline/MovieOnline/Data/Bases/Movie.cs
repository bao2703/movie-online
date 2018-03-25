using System;
using System.ComponentModel.DataAnnotations;

namespace MovieOnline.Data.Bases
{
    public class Movie
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public DateTime Release { get; set; }

        public string Description { get; set; }

        public long Views { get; set; }

        public float Rating { get; set; }

        public string Poster { get; set; }
    }
}