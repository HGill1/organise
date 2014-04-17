/**
 * 
 */
package com.jemmmedia.organise.service.impl.utility;

/**
 * @author harjinder
 *
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class AdditiveMapDecorator<K, V extends Number> implements Map<K, V>
{
    Map<K, V> decoratedMap;

    public AdditiveMapDecorator(Map<K, V> decoratedMap)
    {
        super();
        this.decoratedMap = decoratedMap;
    }

    @Override
    public int size()
    {
        return this.decoratedMap.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.decoratedMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return this.decoratedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return this.decoratedMap.containsValue(value);
    }

    @Override
    public V get(Object key)
    {
        return this.decoratedMap.get(key);
    }

    @Override
    public V put(K key, V value)
    {
        if (key == null)
            return value;

        V previousVal = this.decoratedMap.get(key);
        if (previousVal == null || value == null)
        {
            if (previousVal instanceof AtomicInteger || value instanceof AtomicInteger)
            {
                if (previousVal == null)
                    previousVal = (V) new AtomicInteger(0);
                if (value == null)
                    value = (V) new AtomicInteger(0);
            }
            else if (previousVal instanceof AtomicLong || value instanceof AtomicLong)
            {
                if (previousVal == null)
                    previousVal = (V) new AtomicLong(0);
                if (value == null)
                    value = (V) new AtomicLong(0);
            }
            else if (previousVal instanceof BigDecimal || value instanceof BigDecimal)
            {
                if (previousVal == null)
                    previousVal = (V) BigDecimal.ZERO;
                if (value == null)
                    value = (V) (V) BigDecimal.ZERO;
            }
            else if (previousVal instanceof BigInteger || value instanceof BigInteger)
            {
                if (previousVal == null)
                    previousVal = (V) BigInteger.ZERO;
                if (value == null)
                    value = (V) (V) BigInteger.ZERO;
            }
            else if (previousVal instanceof Byte || value instanceof Byte)
            {
                if (previousVal == null)
                    previousVal = (V) new Byte("0");
                if (value == null)
                    value = (V) (V) new Byte("0");
            }
            else if (previousVal instanceof Double || value instanceof Double)
            {
                if (previousVal == null)
                    previousVal = (V) new Double("0");
                if (value == null)
                    value = (V) (V) new Double("0");
            }
            else if (previousVal instanceof Float || value instanceof Float)
            {
                if (previousVal == null)
                    previousVal = (V) new Float("0");
                if (value == null)
                    value = (V) (V) new Float("0");
            }
            else if (previousVal instanceof Integer || value instanceof Integer)
            {
                if (previousVal == null)
                    previousVal = (V) new Integer("0");
                if (value == null)
                    value = (V) (V) new Integer("0");
            }
            else if (previousVal instanceof Long || value instanceof Long)
            {
                if (previousVal == null)
                    previousVal = (V) new Long("0");
                if (value == null)
                    value = (V) (V) new Long("0");
            }
            else if (previousVal instanceof Short || value instanceof Short)
            {
                if (previousVal == null)
                    previousVal = (V) new Short("0");
                if (value == null)
                    value = (V) (V) new Short("0");
            }
        }

        V newValue = null;
        if (previousVal instanceof AtomicInteger || value instanceof AtomicInteger)
        {
            ((AtomicInteger) previousVal).addAndGet(value.intValue());
        }
        else if (previousVal instanceof AtomicLong || value instanceof AtomicLong)
        {
            ((AtomicLong) previousVal).addAndGet(value.intValue());
        }
        else if (previousVal instanceof BigDecimal || value instanceof BigDecimal)
        {
            newValue = (V) ((BigDecimal) previousVal).add((BigDecimal) value);
        }
        else if (previousVal instanceof BigInteger || value instanceof BigInteger)
        {
            newValue = (V) ((BigInteger) previousVal).add((BigInteger) value);
        }
        else if (previousVal instanceof Byte || value instanceof Byte)
        {
            newValue = (V) new Byte(String.valueOf(previousVal.doubleValue() + value.doubleValue()));
        }
        else if (previousVal instanceof Double || value instanceof Double)
        {
            newValue = (V) new Double(previousVal.doubleValue() + value.doubleValue());
        }
        else if (previousVal instanceof Float || value instanceof Float)
        {
            newValue = (V) new Float(previousVal.floatValue() + value.floatValue());
        }
        else if (previousVal instanceof Integer || value instanceof Integer)
        {
            newValue = (V) new Integer(previousVal.intValue() + value.intValue());
        }
        else if (previousVal instanceof Long || value instanceof Long)
        {
            newValue = (V) new Long(previousVal.longValue() + value.longValue());
        }
        else if (previousVal instanceof Short || value instanceof Short)
        {
            newValue = (V) new Short((short) (previousVal.shortValue() + value.shortValue()));
        }

        return this.decoratedMap.put(key, newValue);
    }

    @Override
    public V remove(Object key)
    {
        return this.decoratedMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (java.util.Map.Entry<? extends K, ? extends V> entry : m.entrySet())
        {
            this.decoratedMap.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear()
    {
        this.decoratedMap.clear();
    }

    @Override
    public Set<K> keySet()
    {
        return this.decoratedMap.keySet();
    }

    @Override
    public Collection<V> values()
    {
        return this.decoratedMap.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet()
    {
        return this.decoratedMap.entrySet();
    }

}
