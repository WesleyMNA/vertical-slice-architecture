package com.example.vertical_slice_architecture.task.shared;

import com.example.vertical_slice_architecture.shared.auth.AuthHelper;
import com.example.vertical_slice_architecture.shared.auth.CurrentUser;
import com.example.vertical_slice_architecture.shared.rest.ForbiddenException;
import com.example.vertical_slice_architecture.task.entities.Task;
import com.example.vertical_slice_architecture.user.shared.UserConstants;
import org.springframework.stereotype.Component;

@Component
public class TaskOwnershipValidator {

    public void validate(Task task) {
        CurrentUser currentUser = AuthHelper.getCurrentUser();

        if (!task.getUserId().equals(currentUser.getId()))
            throw new ForbiddenException(UserConstants.USER_DOES_NOT_HAVE_PERMISSION);
    }
}
