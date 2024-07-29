package uz.itschool.docc.di

import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import org.koin.dsl.module

val networkModule = module {
    single { provideOpenAI() }
}

fun provideOpenAI(): OpenAI {
    val config = OpenAIConfig(
        token = "sk-proj-xnlqfRjBoryqLLdbWL4CT3BlbkFJ4mmrSOdIt0vrSkcXMcee"
    )
    return OpenAI(config)
}