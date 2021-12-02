package com.example.moviekeeper.aggregator;

import com.example.moviekeeper.entity.user.Role;
import com.example.moviekeeper.entity.user.Telephone;
import com.example.moviekeeper.entity.user.User;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class UserAggregatorTest implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return User.builder()
                .id(argumentsAccessor.getLong(0))
                .name(argumentsAccessor.getString(1))
                .username(argumentsAccessor.getString(2))
                .password(argumentsAccessor.getString(3))
                .telephone(Telephone.builder()
                        .id(argumentsAccessor.getLong(4))
                        .number(argumentsAccessor.getString(5))
                        .build())
                .role(Role.USER)
                .build();
    }
}
