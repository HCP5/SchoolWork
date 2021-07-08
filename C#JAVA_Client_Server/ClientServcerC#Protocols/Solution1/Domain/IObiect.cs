using System;

namespace Domain
{
    public interface IObject<TId>
    {
        TId Id { get; set; }
    }
}