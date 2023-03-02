package gr.deque.mobile.app.ws.common.helpers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface DomainMapper<Model, DomainModel> {
    DomainModel modelToDomain(Model model);

    Model domainToModel(DomainModel domainModel);

    default List<Model> mapModelLists(List<DomainModel> domainList) {
        return domainList.stream()
                .filter(Objects::nonNull)
                .map(this::domainToModel)
                .collect(Collectors.toList());
    }

    default List<DomainModel> mapDomainLists(List<Model> modelList) {
        return modelList.stream()
                .filter(Objects::nonNull)
                .map(this::modelToDomain)
                .collect(Collectors.toList());
    }
}