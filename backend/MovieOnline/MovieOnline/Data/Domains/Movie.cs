using System;

namespace MovieOnline.Data.Domains
{
    public class Movie
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public DateTime Release { get; set; }

        public string Description { get; set; }

        public long Views { get; set; }

        public string Image { get; set; }
    }
}