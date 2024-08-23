# KANDUIT-SSO-GATEWAY

This repository contains the source code for the **Single Sign-On (SSO)** functionality, part of the **KANDUIT**
project. This project uses **Spring Boot**.

## Project Purpose

The purpose of this project is to provide a reusable **login system** for new projects. By leveraging or cloning this
SSO system, you can quickly set up authentication for any new project. The functionality will be updated over time
through feature-specific commits. Once the basic **user registration** system and **security settings** are completed,
it will be deployed to the servers listed below.

## API Documentation

You can find document page from [`here`](https://api.kanduit.tech/docs) (under construction)

## Contribution

If you find any **bugs**, **security issues**, or have any **suggestions for improvements**, please feel free to open an
**ISSUE** or a **PULL REQUEST (PR)** to contribute.

## Server List & API Status

| BRANCH   | PROVIDER      | REGION(LOCATION)              | API ENDPOINT                                             | API STATUS                                                                                                                                                                                                 | 
|----------|---------------|-------------------------------|----------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `master` | `OracleCloud` | `SouthKorea-North(Chuncheon)` | [`https://api.kanduit.tech/`](https://api.kanduit.tech/) | ![Website](https://img.shields.io/website?down_color=red&down_message=PREPARING&label=RELEASE%20API&style=for-the-badge&up_color=lightgreen&up_message=ONLINE&url=https%3A%2F%2Fapi.kanduit.tech%2Fstatus) |

### Project Structure

      com.kanduit.sso.main
      ├─java
      │ └─com
      │     └─kanduit
      │         └─sso
      │             ├─application
      │             │   ├─dto
      │             │   │   ├─BaseResponseDTO
      │             │   │   ├─DataResponseDTO
      │             │   │   └─ErrorResponseDTO
      │             │   └─enums
      │             │       ├─ApiError
      │             │       └─ApiStatus
      │             ├─domain
      │             │   ├─enums
      │             │   │   └─**domain-enums
      │             │   ├─factory
      │             │   │   └─ResponseFactory
      │             │   └─service
      │             │       └─FaviconService
      │             ├─exception
      │             │   ├─ApiExceptionBody
      │             │   ├─ApiExceptionFactory
      │             │   ├─ApiExceptionHandler
      │             │   └─ApiException
      │             ├─utils
      │             │   └─ResponseUtil
      │             └─KanduitSsoGatewayApplication
      └─resources
        │   ├─favicons
        │   │   └─**favicons
        │   └─configs
        │       └─database
        │           └─mariadb.properties
        └─application.properties