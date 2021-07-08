namespace Domain.validators.interfaceAndException
{
    public interface IValidator<T>
    {
        void validate(T entity);
    }
}