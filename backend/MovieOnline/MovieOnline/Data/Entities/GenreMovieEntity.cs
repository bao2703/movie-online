namespace MovieOnline.Data.Entities
{
    public class GenreMovieEntity
    {
        public int GenreId { get; set; }

        public GenreEntity Genre { get; set; }

        public int MovieId { get; set; }

        public MovieEntity Movie { get; set; }
    }
}