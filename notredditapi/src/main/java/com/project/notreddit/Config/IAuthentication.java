package com.project.notreddit.Config;

import org.springframework.security.core.Authentication;

public interface IAuthentication {
    Authentication getAuthentication();
}
