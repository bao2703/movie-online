﻿namespace MovieOnline.Data.Bases
{
    public class Episode
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Url { get; set; }

        public int MovieId { get; set; }
    }
}